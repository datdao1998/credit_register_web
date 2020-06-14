

function submitFormChooseClass(id) {
    var r  = confirm("Bạn có chắc chắn muốn chọn lớp học không ?");
    if (r == false) return;
    document.getElementById("choose-class-form-id-" + id).submit();
}