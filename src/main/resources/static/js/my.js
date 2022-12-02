function findBillById_edit(id) {
    var url = "/findBillById";
    $.get(url,"id="+id,function (data) {
        console.log(data)
        $("#eid").val(data.id);
        $("#etitle").val(data.title);
        $("#euserId").val(data.userId);
        $("#emoney").val(data.money);
        $("#etype").val(data.type);
    });
}

function editBill() {
    var url = "/editBill";
    $.get(url,$("#editBill").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}


function deleteBill(id) {
    var b = confirm("是否删除该条记录？");
    if (b) {
        var url = "deleteBill";
        $.get(url, "id=" + id, function (data) {
            alert(data);
            window.location.reload();
        })
    }
}

    function editMessage(){
        var b = confirm("确定修改吗？");
        if (b){
            var url = "editMessage";
            $.get(url,$("#editMessage").serialize(),function (data){
                alert(data);
                window.location.reload();
            })
        }
}

function addBill(){
    var url = "/addBill";
    $.get(url,$("#addBill").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}


