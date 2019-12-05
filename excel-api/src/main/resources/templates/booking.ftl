<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Booking遍历list</title>
</head>

<body>
<div id="main" style="margin: 10px">
<H3 style="border: 1px solid lightsteelblue; text-align: center">Worklist Board</H3>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover  table-condensed table-sm">
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
                <td><a href="gbot:file=${b.hawb_no}">${b.hawb_no}</a></td>
                <td>${b.shipper_name}</td>
                <td>${b.consignee_name}</td>
                <td>${b.notify_party}</td>
            </tr>
        </#list>
        </tbody>
    </table>

    <form class="form-horizontal" id="form_table" action="/booking" enctype="multipart/form-data" method="post">
        <br/>
        <br/>

        <br>

        <span style="height: 120px;font-size: 12px;border: 1px solid lightsteelblue;" class="btn btn-default">拖动文件到这里或点击选择按钮<br>
            <input class="btn btn-default" style="height: 85px;" type="file" name="filename">

        </span>
        <#--<div id="filedrag" class="header-panel">或者将文件拖拽到这里</div>-->
        <#--<div id="submitbutton">-->
        <button type="submit" style="height: 120px;border: 1px solid lightsteelblue;" class="btn btn-default">导入</button>
        <a href="/exportbooking"><button type="button" class="btn btn-default" style="height: 120px; border: 1px solid lightsteelblue;">导出</button></a>
    </form>
    <div id="messages"></div>
</div>
</div>
</body>
</html>