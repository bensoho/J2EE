<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>freemarker遍历list</title>
</head>

<body>
<div class="table-responsive">
    <table class="table">
        <thead class="Table cell">
        <td>ID</td>
        <td>用户名</td>
        <td>密码</td>
        </thead>
        <tbody>
        <#list user as u>

            <tr class="success">
                <td>${u.uid}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/export"><button type="button" class="btn btn-primary">导出</button></a>
    <form class="form-horizontal" id="form_table" action="/import" enctype="multipart/form-data" method="post">
        <br/>
        <br/>
        <button type="submit" class="btn btn-primary">导入</button>
        <input class="form-input" type="file" name="filename"></input>
    </form>
</div>
</body>
</html>