# project
Adcash product-category assignment

##Login 
Login API for Normal User
curl -X POST \
  http://localhost:8080/login \
  -H 'content-type: application/json' \
  -d '{
"username":"user1",
"password":"user1"
}'
##########Admin user 
curl -X POST \
  http://localhost:8080/login \
  -H 'content-type: application/json' \
  -d '{
"username":"admin",
"password":"admin"
}'
##Category
###All crud operations available for admin user only
curl -X POST \
  http://localhost:8080/services/categories \
  -H 'accept: application/xml' \
  -H 'authorization: Bearer xxx' \
  -H 'content-type: application/json'

curl -X POST \
  http://localhost:8080/services/categories/create \
  -H 'accept: application/json' \
  -H 'authorization: Bearer xxx' \
  -H 'content-type: application/json' \
  -d '{
"name":"Hygiene"
}'

##Products
###All CRUD operation applicable for admin user only

curl -X POST \
  http://localhost:8080/services/product/create/ \
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
   -H 'authorization: Bearer xxx' \
  
  -d '{
"name":"toothbrush"
}'
curl -X PATCH \
  http://localhost:8080/services/product/add/ \
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
  -H 'authorization: Bearer xxx' \
  -d '{
"name":"toothbrush",
"categories":["Hygiene"]
}'


