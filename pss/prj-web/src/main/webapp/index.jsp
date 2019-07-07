<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>demo</title>
    <script src="http://oss.sheetjs.com/js-xlsx/xlsx.core.min.js"></script>
    <script src="xlsx.utils.min.js"></script>
    <script src="saveAs.min.js"> </script>
</head>

<body>
<script>
    var data = [{ 主页: "111", 名称: "6800", 数量: "6800", 昵称: "广告主网" }, { 主页: "433", 名称: "6800", 数量: "6800", 昵称: "广告主网" }, { 名称: "22", 商家: "6800", 数量: "6800", 昵称: "广告主网", }, { 名称: "43", 商家: "6800", 数量: "6800", 昵称: "广告主网", }, { 店家: "43", 价格: "6800", 数量: "6800", 昵称: "广告主网", }];
</script>
<button id="down">导出</button>
<script>
    var down = document.getElementById("down");
    down.onclick = function () {
        data.unshift(xlsxUtils.readDataHead(data));
        var blob = xlsxUtils.export({ "Sheet1": data, "Sheet2": [{ "a": "A", "b": "B" }, { "a": 1, "b": "2" }, { "a": 3, "b": 4 }, { "a": 5, "b": 6 }] });
        saveAs(URL.createObjectURL(blob), "aa.xlsx");
    };
</script>
</body>

</html>
