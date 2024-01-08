function validatePassword() {
    let password = document.getElementById("memberPw").value;
    let confirmPassword = document.getElementById("memberPwCheck").value;

    let passwordError = document.getElementById("passwordError");
    let confirmPasswordError = document.getElementById("confirmPasswordError");
    let submitButton = document.getElementById("submitButton");


    if (password.trim() == "") {
        passwordError.innerText = "새롭게 적용할 비밀번호를 입력하세요.";
        submitButton.disabled = true;
    } else if (password.length < 8) {
        passwordError.innerText = "비밀번호는 8글자 이상이어야 합니다.";
        submitButton.disabled = true;
    } else {
        passwordError.innerText = "";
        if (password !== confirmPassword) {
            confirmPasswordError.innerText = "비밀번호가 일치하지 않습니다.";
            submitButton.disabled = true;
        } else {
            confirmPasswordError.innerText = "";
            submitButton.disabled = false;
        }
    }
}
// function submitForm() {
//     let password = document.getElementById("memberPw").value;
//     let confirmPassword = document.getElementById("memberPwCheck").value;
//
//     let passwordError = document.getElementById("passwordError");
//     let confirmPasswordError = document.getElementById("confirmPasswordError");
//
//     if (password.trim() === "") {
//         passwordError.innerText = "비밀번호를 입력하세요.";
//     }
//     if (confirmPassword.trim() === "") {
//         confirmPasswordError.innerText = "비밀번호를 확인하세요.";
//     }
//
//     // 실제로 폼을 제출하는 로직
//     if (password.trim() !== "" && confirmPassword.trim() !== "") {
//         document.getElementById("passwordForm").submit();
//     }
// }
