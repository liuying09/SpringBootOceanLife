CREATE TABLE IF NOT EXISTS OCEAN_activity (
  activityID INT NOT NULL AUTO_INCREMENT,
  activityTitle VARCHAR(45),
  activityContent VARCHAR(45),
  activityDate VARCHAR(45),
  activityTime VARCHAR(45),
  activityImg BLOB,
  activityRemark VARCHAR(45),
  activityStatus VARCHAR(45),
  create_date VARCHAR(45),
  update_date VARCHAR(45),
  PRIMARY KEY (activityID)
);

CREATE TABLE IF NOT EXISTS OCEAN_article (
  articleID INT NOT NULL AUTO_INCREMENT,
  valueEN VARCHAR(45),
  articleImg BLOB,
  articleTitle VARCHAR(45),
  articleContent VARCHAR(45),
  articleRemark VARCHAR(45),
  articleStatus VARCHAR(45),
  create_date VARCHAR(45),
  update_date VARCHAR(45),
  PRIMARY KEY (articleID)
);

CREATE TABLE IF NOT EXISTS OCEAN_product (
  productId INT NOT NULL AUTO_INCREMENT,
  productName VARCHAR(45),
  productPrice VARCHAR(45),
  productPriceSale VARCHAR(45),
  productImg VARCHAR(45),
  productType VARCHAR(45),
  productNum VARCHAR(45),
  productContent VARCHAR(45),
  productSpenMaterial VARCHAR(45),
  productSpenSize VARCHAR(45),
  productSpenMF VARCHAR(45),
  productRemark VARCHAR(45),
  productStatus VARCHAR(45),
  create_date VARCHAR(45),
  update_date VARCHAR(45),
  PRIMARY KEY (productId)
);

CREATE TABLE IF NOT EXISTS OCEAN_productimg (
  productImgID INT NOT NULL AUTO_INCREMENT,
  productID VARCHAR(45),
  productImgName VARCHAR(45),
  productImgBlob BLOB,
  create_date VARCHAR(45),
  update_date VARCHAR(45),
  PRIMARY KEY (productImgID)
);

CREATE TABLE IF NOT EXISTS OCEAN_user (
  userId INT NOT NULL AUTO_INCREMENT,
  userSalt VARCHAR(45),
  userAccount VARCHAR(45),
  userPass VARCHAR(45),
  userName VARCHAR(45),
  userGender VARCHAR(45),
  userStatus VARCHAR(45),
  userBirth VARCHAR(45),
  userCountry VARCHAR(45),
  userDistrict VARCHAR(45),
  userRoad VARCHAR(45),
  userPhone VARCHAR(45),
  userImg BLOB,
  userSub VARCHAR(45),
  favoriteID VARCHAR(45),
  create_date VARCHAR(45),
  update_date VARCHAR(45),
  PRIMARY KEY (userId)
);
