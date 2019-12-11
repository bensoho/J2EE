<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Booking遍历list</title>

    <style>
        div.sticky {
            position: -webkit-sticky;
            position: sticky;
            bottom: 0;
            padding: 5px;
            background-color: lightgoldenrodyellow;
            border: 1px solid lightgray;
        }
    </style>
</head>

<body>

<div id="main" style="margin: 10px" class="container">
    <H3 style="text-align: center">WORKBOARD</H3>
    <div>
        <table class="table table-striped table-bordered table-hover table-sm" style="font-size: smaller">
            <thead>
            <td>ID</td>
            <td>HAWB</td>
            <td>DEST</td>
            <td>Shipper_GCI</td>
            <td>Shipper</td>
            <td>Consignee_GCI</td>
            <td>Consignee</td>
            <td>Notify Party</td>
            <td>Verify Client Info</td>

            </thead>
            <tbody>
            <#list booking as b>

                <tr>
                    <td>${b.bid}</td>
                    <td><a href="gbot:file=${b.hawb_no}">${b.hawb_no}</a></td>
                    <td>dest</td>
                    <td>${b.shipper_gci}</td>
                    <td>${b.shipper_name}</td>
                    <td>${b.consignee_gci}</td>
                    <td>${b.consignee_name}</td>
                    <td>${b.notify_party}</td>
                    <td>
                        <button id="${b.bid}"  name="${b.shipper_name}" type="button" class="btn btn-xs btn-primary test" data-toggle="modal" data-target="#myModal">Verify(S)</button>
                        <button id="${b.bid}"  name="${b.consignee_name}" type="button" class="btn btn-xs btn-primary test" data-toggle="modal" data-target="#myModal">Verify(C)</button>

                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="sticky">
        <form class="form-horizontal" id="form_table" action="/booking" enctype="multipart/form-data" method="post">

            <span style="height: 120px;font-size: 12px;border: 1px solid lightsteelblue;" class="btn btn-default">拖动文件到这里或点击选择按钮<br>
                <input class="btn btn-default" style="height: 85px;" type="file" name="filename">

            </span>
            <#--<div id="filedrag" class="header-panel">或者将文件拖拽到这里</div>-->
            <#--<div id="submitbutton">-->
            <button type="submit" style="height: 120px;border: 1px solid lightsteelblue;" class="btn btn-default">导入</button>
            <a href="/exportbooking"><button type="button" class="btn btn-default" style="height: 120px; border: 1px solid lightsteelblue;">导出</button></a>
        </form>
    </div>
        <br>
    <div id="ebook">
        <span><a href="/ebook">Ebooking</a> </span>
    </div>




    <script>
        $(function () {
            $(".test").click(function () {
                var id = $(this).attr("id");
                var name = $(this).attr("name");

                //alert("/search?name=" + name);

                $.ajax({
                    url: "/search",
                    data:{"name":name},
                    success: function(result){
                        //alert(result["company_id"]);
                        var company_id = result["company_id"];
                        var company_name = result["company_name"];
                        var address_1 = result["address_1"];
                        var address_2 = result["address_2"];
                        var city = result["city"];
                        var country_code = result["country_code"];
                        var country_name = result["country_name"];
                        $("#company_id").html(company_id);
                        $("#company_name").html(company_name);
                        $("#address_1").html(address_1);
                        $("#address_2").html(address_2);
                        $("#city").html(city);
                        $("#country_code").html(country_code);
                        $("#country_name").html(country_name);

                    }
                })

            })
        });




    </script>

    <#--<span>输入账号 :</span>-->
    <#--<input id="name" name="name" onclick="check()" type="text">-->
    <#--<span id="checkResult"></span>-->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">GCI Information</h5>
                </div>
                <div class="modal-body">

                    <div id="checkResult">
                        <table class="table table-striped table-xs">
                            <tr>
                                <td>company_id</td>
                                <td id="company_id"></td>
                            <tr>
                                <td>company_name</td>
                                <td id="company_name"></td>
                            </tr>
                            <tr>
                                <td>address_1</td>
                                <td id="address_1"></td>
                            </tr>
                            <tr>
                                <td>address_2</td>
                                <td id="address_2"></td>
                            </tr>
                            <tr>
                                <td>city</td>
                                <td id="city"></td>
                            </tr>
                            <tr>
                                <td>country_code</td>
                                <td id="country_code"></td>
                            </tr>
                            <tr>
                                <td>country_name</td>
                                <td id="country_name"></td>
                            </tr>
                            </tr>
                        </table>
                    </div>


                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-sm btn-info" type="button">关闭</button>
                    <#--<button class="btn btn-primary" type="button">提交</button>-->
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <div style="height:150px"></div>


</div>
</body>
</html>