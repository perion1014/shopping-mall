
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
            location.reload();
            //alert(currentQuantityNum);
            //alert('전송 결과 : ' + data);
        }).catch(error => {
            alert('전송 실패 - Error : ' + error);
    })

}

function deleteCart(index, cartNo, memberNo){
    let deleteCheck = confirm('장바구니에서 해당 항목을 삭제하시겠습니까?');
    if(deleteCheck == true){
        fetch('/carts/' + memberNo + '/' + cartNo + '/delete',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify({
                cartNo: cartNo,
                memberNo: memberNo,
            }),
        }).then(response => response.text())
            .then(date => {
                alert("삭제되었습니다.");
                location.reload();
            }).catch(error =>{
                alert("삭제에 실패하였습니다 - 사유 : " + error);
        })
    } else{
        alert("취소하셨습니다.");
    }
}