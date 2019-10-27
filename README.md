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
  http://localhost:8080/services/categories/create \
  -H 'accept: application/json' \
  -H 'authorization: Bearer xxx' \
  -H 'content-type: application/json' \
  -d '{
"name":"Electronics"
}'

curl -X PUT \
  http://localhost:8080/services/categories/update/Electronics?newCategoryName=Games\
  -H 'accept: application/json' \
  -H 'authorization: Bearer xxx' \
  -H 'content-type: application/json'
  
curl -X DELETE \
  http://localhost:8080/services/categories/delete/Games\
  -H 'accept: application/json' \
  -H 'authorization: Bearer xxx' \
  -H 'content-type: application/json'

##Products
###All CRUD operation applicable for admin user only

curl -X POST \
  http://localhost:8080/services/product/create/ \
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
   -H 'authorization: Bearer xxx' \
  
  -d '{
"name":"toys"
}'
curl -X PATCH \
  http://localhost:8080/services/product/add/ \
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
  -H 'authorization: Bearer xxx' \
  -d '{
"name":"machineGuns",
"categories":["Electronics"]
}'
curl -X PUT \
  http://localhost:8080/services/product/update/machineGuns?newProductName=Scooters \
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
   -H 'authorization: Bearer xxx'

curl -X DELETE \
  http://localhost:8080/services/product/delete/machineGuns\
  -H 'accept: application/xml' \
  -H 'content-type: application/json' \
   -H 'authorization: Bearer xxx' \
