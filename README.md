# file-upload-download-with-microservices
The project contains the main service to upload/download a file with a eureka service discovery and gateway. Make sure to run all 3 services at the same time.

File upload link: localhost:5555/fileuploaddownloadservice/file/upload

File download link: localhost:5555/fileuploaddownloadservice/file/download/{versionName} 

P.S. get versionName from localhost:8081/h2-console to download a file

Here are small tips about how you can upload/download a file via postman:
  https://stackoverflow.com/questions/39037049/how-to-upload-a-file-and-json-data-in-postman
  https://stackoverflow.com/questions/38975718/how-to-download-excel-xls-file-from-api-in-postman
  
  P.S. change the path in src/main/resources/application.properties
