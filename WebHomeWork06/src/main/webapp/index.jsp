<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forward and Redirect Test"</title>
    <style>
        h1 {
            text-align: center;
        }
        h3 {
            text-align: center;
        }

        form {
            width: 300px;
            margin: 0 auto;
        }
        .attribute {
            display: inline-block;
            width: 75px;
            text-align: justify;
            text-align-last: justify;
            text-justify: distribute-all-lines;
        }
        .item {
            margin: 10px 0;
            margin-left: 20px;
        }

    </style>
</head>
<body>
<h1><%= "Forward and Redirect Test" %>
</h1>
<br/>
<div>
<h3>Forward Test</h3>
<form action="redirect01" method="post">
    <fieldset>
        <legend>Param Test</legend>
        <div class="item">
            <span class="attribute">Param1</span>: <input type="text" name="param1" value=""></div>
        <div class="item">
            <span class="attribute">Param2</span>: <input type="text" name="param2" value=""></div>
        <div style="text-align: center;">
        <input type="submit" value="Submit" class="button" >
        </div>
    </fieldset>
</form>
</div>
<div style="text-align: center;">
    <h3>Redirect Test</h3>
    <a href="redirect02">
        <button>Redirect Test</button>
    </a>
</div>
</body>
</html>