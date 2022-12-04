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

function register(){
    var url = "/register";
    $.get(url,$("#addUser").serialize(),function (data){
    alert(data);
    window.location.reload();
    })
}

function deleteUser(id){
    var b = confirm("是否删除该账户？");
    if (b) {
        var t = confirm("该操作不可逆，是否继续?");
        if (t){
            var url = "deleteUser";
            $.get(url, "id=" + id, function (data) {
                alert(data);
                window.location.reload();
            })
        }
    }
}

function findUserById_edit(id) {
    var url = "/findUserById";
    $.get(url,"id="+id,function (data) {
        console.log(data)
        $("#bid").val(data.id);
        $("#busername").val(data.username);
        $("#bhouseId").val(data.houseId);
    });
}

function changeUser(){
    var url = "/editUser";
    $.get(url,$("#editUser").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
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


