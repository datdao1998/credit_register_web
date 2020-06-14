document.getElementById("save-form-btn").onclick = saveLearnedForm;

function saveLearnedForm() {
    var totalCredit = Number(document.getElementById("total-credit").value);

    if(totalCredit < 14 || totalCredit > 22){
        alert("Số tín chỉ từ 14 đến 22 tín chỉ");
    }
    else{
        var r = confirm("Bạn có chắc chắn muốn lưu đăng ký hay không??");
        if (r==false) return;
        document.getElementById("save-learn-form").submit();
    }
}