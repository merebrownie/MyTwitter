<%-- 
    Document   : upload
    Created on : Nov 14, 2015, Nov 14, 2015 1:12:03 AM
    Author     : meredithbrowne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload</title>
    </head>
    <body>
        <h1>File Upload</h1>
        <form method="post" action="UploadServlet"
              enctype="multipart/form-data">
            Select file to upload: 
            <input type="file" name="file" value="${user.profilePicture}" size="60" />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
