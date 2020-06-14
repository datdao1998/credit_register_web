document.getElementById("subject-btn-search").onclick = submitFormSearch;

function submitFormSearch() {
    var subject = document.getElementById("subject-input").value;
    if (subject.includes("!") || subject.includes("/") || subject.includes("|") || subject.includes("#") ||
        subject.includes("$") || subject.includes("%") || subject.includes("^") || subject.includes(">") ||
        subject.includes("<") || subject.includes("*") || subject.includes("(") || subject.includes(")") ||
        subject.includes(",") || subject.includes("?") || subject.includes(";") || subject.includes(":") ||
        subject.includes("&") || subject.includes("-") || subject.includes("+") || subject.includes("=") ||
        subject.includes(" ") || subject.includes("@")) {
        alert("Tên môn học không được chứa ký tự đặc biệt");
        return;
    }

    if (subject.toString().toLowerCase().substring(subject.toString().length - 7, subject.toString().length) == "or true") {
        alert("Tên môn học không được chứa ký tự đặc biệt");
        return;
    }

    if(subject==""){
        alert("Không được để trống mã môn học");
        return;
    }

    document.getElementById("subject-form-search").submit();
}