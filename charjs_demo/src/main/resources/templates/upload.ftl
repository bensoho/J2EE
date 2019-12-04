<!DOCTYPE html>
<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Hello Expeditors,</h1><br>
<p>当前时间：${.now?string("yyyy-MM-dd HH:mm:ss.sss")}</p>

<form class="form-horizontal" action="/import" method="post" enctype="multipart/form-data">
    <input class="form-input" type="file" name="filename">
    <button type="submit" class="btn btn-primary">导入</button>
</form>


</body>
</html>
