<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/tlds/cpe3243tag_library" prefix="cpe3243" %>
<html>
    <head>
        <title>Currency Converter</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                background-image: url('https://mir-s3-cdn-cf.behance.net/project_modules/1400_opt_1/01073865290819.5d61d475f0072.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
            }

            .container {
                background: rgba(255, 255, 255, 0.9);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                padding: 20px 30px;
                width: 400px;
                text-align: center;
            }

            h1 {
                font-size: 24px;
                margin-bottom: 10px;
                color: #444;
            }

            hr {
                margin: 20px 0;
                border: 0;
                height: 1px;
                background: #ddd;
            }

            form {
                text-align: left;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            td {
                padding: 10px 0;
            }

            input[type="text"], select {
                width: 100%;
                padding: 8px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }

            input[type="submit"] {
                background: #007bff;
                color: #fff;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                width: 100%;
            }

            input[type="submit"]:hover {
                background: #0056b3;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            .info {
                font-size: 12px;
                color: #666;
            }
        </style>
    </head>
    <body>
        <cpe3243:Tag01 />
    </body>
</html>
