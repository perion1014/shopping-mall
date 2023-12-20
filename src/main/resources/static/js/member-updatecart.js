
function updateQuantity(changeQuantity, index, cartNo, memberNo){
    let currentQuantity = document.getElementById('inputvalue_' + index);
    let currentQuantityNum = Number(currentQuantity.value);
    currentQuantityNum += Number(changeQuantity);
    currentQuantity.value = currentQuantityNum;

    fetch('/carts/' + memberNo + '/' + cartNo + '/update',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            currentQuantityNum: currentQuantityNum,
            cartNo: cartNo,
            memberNo: memberNo,
        }),
    }).then(response => response.text())
        .then(data => {
            alert(currentQuantityNum);
            alert('전송 결과 : ' + data);
        }).catch(error => {
            alert('전송 실패 - Error : ' + error);
    })

}