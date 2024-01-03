function change_form(type) {
    if (type == "findIdRadio") {
        document.getElementById("display1").style.display = "block";    //tripA 보이게 하는 부분. type은 block 말고 다른 것들도 있어요. 적절한 거 쓰시면 됩니다.
        document.getElementById("display2").style.display = "none";    //tripB 보이지 않게 하는 부분
    } else if (type == "findPwRadio") {
        document.getElementById("display1").style.display = "none";
        document.getElementById("display2").style.display = "block";
    }
}