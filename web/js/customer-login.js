document.getElementById("customer-login-btn-login").onclick = login;

function login() {
    var userName = document.getElementById("customer-login-input-user-name").value;
    var password = document.getElementById("customer-login-input-password").value;

    if (userName =="") {
        alert("Không được để trống tên đăng nhập");
        return;
    }

    if (password.length < 8 ){
        alert("Mật khấu phải ít nhất 8 ký tự");
        return;
    }
    if (userName.includes("!") || userName.includes("/") || userName.includes("|") || userName.includes("#") ||
        userName.includes("$") || userName.includes("%") || userName.includes("^") || userName.includes(">") ||
        userName.includes("<") || userName.includes("*") || userName.includes("(") || userName.includes(")") ||
        userName.includes(",") || userName.includes("?") || userName.includes(";") || userName.includes(":") ||
        userName.includes("&") || userName.includes("-") || userName.includes("+") || userName.includes("=") ||
        userName.includes(" ")) {
        alert("Tên đăng nhập không được chứa ký tự đặc biệt");
        return;
    }

    if (userName.length < 3 || userName.length > 30){
        alert("Tên đăng nhập phải trong khoảng 3-30 ký tự");
        return;
    }
    if (password =="") {
        alert("Không được để trống mật khẩu");
        return;
    }
    if (userName.toString().toLowerCase().substring(userName.toString().length - 7, userName.toString().length) == "or true") {
        alert("Tên đăng nhập không được chứa SQL Injection ");
        return;
    }

    if (password.toString().toLowerCase().substring(password.toString().length - 7, password.toString().length) == "or true") {
        alert("Mật khẩu không được chứa SQL Injection ");
        return;
    }

    document.getElementById("customer-login-form").submit();
}