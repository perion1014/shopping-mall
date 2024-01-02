function changeCartAddQuantity(number, inputname){

    let nowQuantityInput = document.getElementById(inputname);
    let nowQuantityValue = Number(nowQuantityInput.value);
    if(Number(number) == 1){
        if(nowQuantityValue != 99){
            nowQuantityValue += Number(number);
            nowQuantityInput.value = nowQuantityValue;
        }
    } else if(Number(number) == -1){
        if(nowQuantityValue != 1){
            nowQuantityValue += Number(number);
            nowQuantityInput.value = nowQuantityValue;
        }
    }
}