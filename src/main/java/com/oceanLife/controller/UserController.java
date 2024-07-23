package com.oceanLife.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oceanLife.model.bean.UserModel;
import com.oceanLife.model.req.LoginRequest;
import com.oceanLife.model.req.UserCreateDTO;
import com.oceanLife.model.res.LoginResponse;
import com.oceanLife.security.TokenService;
import com.oceanLife.security.UserIdentity;
import com.oceanLife.service.MailService;
import com.oceanLife.service.UserService;
import com.oceanLife.utils.UserCodeValidate;
import com.oceanLife.utils.enumlist.UserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "會員") //swagger api標題
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UserIdentity userIdentity;
	
    @Autowired
    private MailService mailService;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private UserCodeValidate userCodeValidate;
    
    @SecurityRequirements
    @Operation(summary = "登入", description = "使用者登入")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		 LoginResponse res = tokenService.createToken(request);
		return ResponseEntity.ok(res);
	}
	
    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        Map<String, String> res = Map.of("accessToken", accessToken);

        return ResponseEntity.ok(res);
    }
	
    @SecurityRequirements
    @Operation(summary = "新增、更新使用者", description = "新增、更新使用者資料")
	@PostMapping()
	public ResponseEntity<Map<String, Object>> upsert(@RequestBody UserCreateDTO userCreateDTO){
    	Map<String, Object> response = new HashMap<>();
    	int actionType = userCreateDTO.getActionType();
    	String userAccount = userCreateDTO.getUserModel().getUserAccount();
    	
		try {
			/* create */
			if(actionType == 1) {
				if (!userService.isUserExist(userAccount)) {
					
					// 確認驗證碼
					int status = userCodeValidate.codeValidate(userAccount, userCreateDTO.getAuthCode());
					if(status==UserCodeValidate.CHECKCODE.notExist) {		
						response.put("message", "使用者新增 - 失敗，驗證碼失效");
					    return ResponseEntity.badRequest().body(response);
					    
					}
					if(status==UserCodeValidate.CHECKCODE.NoMatch) {
						response.put("message", "使用者新增 - 失敗，無輸入驗證碼或驗證碼輸入錯誤");
					    return ResponseEntity.badRequest().body(response);
					}
					
					userService.upsert(userCreateDTO);
				    response.put("message", "使用者新增 - 成功");
			        return ResponseEntity.ok(response);
				}
				response.put("message", "使用者新增 - 失敗，此帳號已存在");
			    return ResponseEntity.badRequest().body(response);
			
			/* update */
			}else {
				userService.upsert(userCreateDTO);
			    response.put("message", "使用者更新 - 成功");
			    return ResponseEntity.ok(response);
			}
	        
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Error creating or updating user.");
		}
	}
    
    @Operation(summary = "取得使用者清單", description = "取得全部使用者清單")
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getUsers(
    	    @Parameter(description = "排序欄位")
    		@RequestParam(name = "sortby", defaultValue = "userId") String sortBy,
    		@Parameter(description = "排序升降冪")
            @RequestParam(name = "direction", defaultValue = "ASC") Direction direction,
            @Parameter(description = "當前頁數")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "每筆頁數，預設10筆")
            @RequestParam(name = "size", defaultValue = "10") int size){
    	
    	Map<String, Object> response = new HashMap<>();
    	Page<UserModel> userPage = userService.getUsers(PageRequest.of(page, size, Sort.by(direction, sortBy)));
    	List<UserModel> userList = userPage.getContent();
    	if(!userList.isEmpty()) {
    		response.put("message", "使用者 - 搜尋成功");
    		response.put("user", userList);
            response.put("totalElements", userPage.getTotalElements()); // 總筆數
            response.put("totalPages", userPage.getTotalPages()); // 總頁數
            response.put("currentPage", userPage.getNumber()); // 目前頁碼
            response.put("pageSize", userPage.getSize()); // 每頁幾筆
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}else {
    		response.put("message", "使用者 - 搜尋成功，目前無任何使用者");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    }
    
    
//    @Operation(summary = "搜尋使用者", description = "搜尋出全部使用者資料")
//    @GetMapping()
//    public ResponseEntity<Map<String, Object>> getUsers(Pageable pageable){
//    	Map<String, Object> response = new HashMap<>();
//    	Page<UserModel> list = userService.getUsers(pageable);
//    	
//    	if(!list.isEmpty()) {
//    		response.put("message", "使用者 - 搜尋成功");
//    		response.put("user", list);
//    		return ResponseEntity.status(HttpStatus.OK).body(response);
//    	}else {
//    		response.put("message", "使用者 - 搜尋成功，目前無任何使用者");
//    		return ResponseEntity.status(HttpStatus.OK).body(response);
//    	}
//    }

    @Operation(summary = "取得單一使用者", description = "使用ID取得指定使用者")
    @GetMapping("{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable int userId){
    	Map<String, Object> response = new HashMap<>();
    	UserModel user = userService.getByUserId(userId);
    	
		response.put("message", "使用者 - 搜尋成功");
		response.put("user", user);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    

    @Operation(summary = "停用使用者", description = "將使用者狀態設為停用")
    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
    	Map<String, Object> response = new HashMap<>();
    	
    	userService.disableUser(id);
    	response.put("message", "使用者 - 停用成功");
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @SecurityRequirements
    @Operation(summary = "使用者發送信箱驗證", description = "使用者發送信箱驗證")
    @PostMapping("/sendCodeMail/{mail}")
    public ResponseEntity<Map<String, Object>> sendCodeMail(@PathVariable String mail){
    	Map<String, Object> response = new HashMap<>();
    	
    	if (userService.isUserExist(mail)){
	        response.put("message", "註冊 - 此帳號已存在");
	        return ResponseEntity.badRequest().body(response);
    	}
    	
    	String datastr =  (String) redisTemplate.opsForValue().get(mail);
    	if(datastr != null && !"".equals(datastr)) {
    		JSONObject data = new JSONObject(datastr);
    		long timestamp = data.getLong("time");
    		if(timestamp >= System.currentTimeMillis()) {
    	        response.put("message", "註冊 - 驗證信已發送過,操作過於頻繁");
    	        return ResponseEntity.badRequest().body(response);
    		}
    	}
    	
    	Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String code = String.format("%06d", number);
    	
    	boolean isSend = mailService.sendCodeMail(mail, code);
		if(isSend) {
			
			JSONObject jsonObject = new JSONObject();
			
			Instant now = Instant.now();
			// 1分鐘過後可再發送一次驗證碼
			long oneMinuteLaterTimestamp = now.plus(Duration.ofMinutes(1)).toEpochMilli();
			 
			jsonObject.put("code", code);
			jsonObject.put("time", oneMinuteLaterTimestamp);
			
			// 設定10分鐘過期
			redisTemplate.opsForValue().set(mail, jsonObject.toString(), 10 ,TimeUnit.MINUTES);
		    response.put("message", "註冊 - 驗證信發送");
		    
		    return ResponseEntity.ok(response);
		}else {
		    throw new RuntimeException("Error! sending mail is fail");
		}
    }
   
}
