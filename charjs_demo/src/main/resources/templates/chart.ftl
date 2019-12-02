<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<script src="https://how2j.cn/study/js/chartjs/2.8.0/chart.min.js"></script>

<div style="width:400px;margin:0px auto">
    <canvas id="myChart" ></canvas>
</div>
<script>
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['红', '蓝', '黄', '绿', '紫', '橙'],
            datasets: [{
                label: '示例',
                data: [12, 19, 3, 5, 2, 3],
                borderColor:'blue',
                borderWidth: 1,
                fill: false,
            }]
        },
        options: {
            tooltips: {
                intersect: false,
                mode: 'index'
            }
        }
    });
</script>
</body>
</html>