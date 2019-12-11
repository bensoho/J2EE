<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://how2j.cn/study/jquery.min.js"></script>
    <title>Springboot + es 构建客户信息检索系统</title>
</head>
<body>


<span>输入账号 :</span>
<input id="name" name="name" onkeyup="check()" type="text">
<span id="checkResult"></span>
<br>
<script>
    var xmlhttp;
    function check(){
        var name = document.getElementById("name").value;
        var url = "/search?name="+name;

        xmlhttp =new XMLHttpRequest();
        xmlhttp.onreadystatechange=checkResult; //响应函数
        xmlhttp.open("GET",url,true);   //设置访问的页面
        xmlhttp.send(null);  //执行访问
    }

    function checkResult(){
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
            document.getElementById('checkResult').innerHTML=xmlhttp.responseText;

    }

</script>


</body>
</html>