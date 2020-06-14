
function submitFormChangeStatusClass(id) {
    var r  = confirm("Bạn có chắc chắn muốn xóa lớp học không ?");
    if (r == false) return;
    document.getElementById("change-status-class-form-" + id).submit();
}