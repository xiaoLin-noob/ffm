function findBillById_edit(id) {
    var url = "/findBillById";
    $.get(url,"id="+id,function (data) {
        $("#epay").val(data.payWayId);
        $("#eid").val(data.id);
        $("#etitle").val(data.title);
        $("#euserId").val(data.userId);
        $("#emoney").val(data.money);
        $("#etime").val(data.time);
        // $("#etype").val(data.type);
        $("input[name=type][value='0']").attr("checked",data.type == 0 ? true : false);
        $("input[name=type][value='1']").attr("checked",data.type == 1 ? true : false);
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
    let b = confirm("确定修改吗？");
    if (b){
        let url = "editMessage";
        $.get(url,$("#editMessage").serialize(),function (data){
            alert(data);
            window.location.href = "user";
        })
    }
}



function findLoanById_edit(id) {
    var url = "/findLoanById";
    $.get(url,"id="+id,function (data) {
        $("#eid").val(data.id);
        $("#emoney").val(data.money);
        $("#ewhere").val(data.where);
        $("#erates").val(data.rates);
        $("#estart").val(data.startDate);
        $("#eend").val(data.endDate);
        $("#epayBack").val(data.payBack);
        $("#emsg").val(data.msg);
        $("#estatus").val(data.status);
    });
}

function addLoan(){
    var url = "/addLoan";
    $.get(url,$("#addLoan").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}

function editLoan() {
    var url = "/editLoan";
    $.get(url,$("#editLoan").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}


function deleteLoan(id) {
    var b = confirm("是否删除该条记录？");
    if (b) {
        var url = "deleteLoan";
        $.get(url, "id=" + id, function (data) {
            alert(data);
            window.location.reload();
        })
    }
}

function editImg(file){
    var url = "editImg";
    $.get(url, "file=" + file, function (data) {
        alert(data);
        window.location.reload();
    })
}

function billSearch(title,type){
    var url = "bill";
    $.get(url,"title="+title+"&type="+type,function (){

    })
}
function findInvestById_edit(id) {
    var url = "/findInvestById";
    $.get(url,"id="+id,function (data) {
        $("#eid").val(data.id);
        $("#ename").val(data.name);
        $("#emoney").val(data.money);
        $("#erate").val(data.rate);
        $("#estart").val(data.startDate);
        $("#eend").val(data.endDate);
        $("#emsg").val(data.msg);
        $("#eincome").val(data.income);

    });
}

function addInvest(){
    var url = "/addInvest";
    $.get(url,$("#addInvest").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}

function editInvest() {
    var url = "/editInvest";
    $.get(url,$("#editInvest").serialize(),function (data){
        alert(data);
        window.location.reload();
    })
}


function deleteInvest(id) {
    var b = confirm("是否删除该条记录？");
    if (b) {
        var url = "/deleteInvest";
        $.get(url, "id=" + id, function (data) {
            alert(data);
            window.location.reload();
        })
    }
}

function banUser(id,status) {
    let url = "/editUser";
    $.get(url,"id="+id+"&status="+status,function (data){
        alert(data);
        window.location.reload();
    })
}
