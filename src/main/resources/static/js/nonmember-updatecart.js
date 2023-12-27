//alert('nonmember-updatecart.js 적용 확인');

function updateQuantity(changeQuantity, cartListIndex){
    let currentQuantityInput = document.getElementById('inputvalue_' + cartListIndex);
    let currentQuantityValue = Number(currentQuantityInput.value);
    //alert(currentQuantityValue);
    if(changeQuantity == 1){
        if(currentQuantityValue != 99){
            currentQuantityValue += Number(changeQuantity);
        }
    } else if(changeQuantity == -1){
        if(currentQuantityValue != 1){
            currentQuantityValue += Number(changeQuantity);
        }
    }

    currentQuantityInput.value = currentQuantityValue;

    fetch('/carts/' + cartListIndex + '/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            changeQuantity: changeQuantity,
            cartListIndex: cartListIndex,
        }),
    }).then(response => response.text())
        .then(data => {
            //alert('JSON JAVA로 전송 성공');
        }).catch(error => {
            alert('JSON 전송 실패 - 사유 : ' + error);
    })

}