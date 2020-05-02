# Task1

## h2
h2 jdbc url must be specfied in application.properties

## get
localhost:8080/api/{appCode}/config # returns all available versions for specified appCode
localhost:808/api/{appCode}/config/{version} # returns config for specified appCode and version

## post
localhost:808/api/{appCode}/config/{version} # creates new databse entry for specified appCode and version with config specifed in the request body
