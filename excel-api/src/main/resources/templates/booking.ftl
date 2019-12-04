<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <title>Booking遍历list</title>
</head>

<body>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover  table-condensed">
        <thead>
        <td>ID</td>
        <td>hawb</td>
        <td>shipper</td>
        <td>consignee</td>
        <td>notify party</td>
        </thead>
        <tbody>
        <#list booking as b>

            <tr class="success">
                <td>${b.bid}</td>
                <td>${b.hawb_no}</td>
                <td>${b.shipper_name}</td>
                <td>${b.consignee_name}</td>
                <td>${b.notify_party}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/exportbooking"><button type="button" class="btn btn-primary">导出</button></a>
    <form class="form-horizontal" id="form_table" action="/booking" enctype="multipart/form-data" method="post">
        <br/>
        <br/>
        <button type="submit" class="btn btn-primary">导入</button>
        <br>
        <input class="form-input" type="file" name="filename">
    </form>
</div>
</body>
</html>