
function updateQuantity(changeQuantity, index, cartNo, memberNo, itemNo, itemSize){
    let currentQuantity = document.getElementById('inputvalue_' + index);
    let currentQuantityNum = Number(currentQuantity.value);

    currentQuantityNum += Number(changeQuantity);
        if(currentQuantityNum == 0) {currentQuantityNum = 1;}
        else if(currentQuantityNum == 100) {currentQuantityNum = 99;}
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
            itemNo: itemNo,
            itemSize: itemSize,
        }),
    }).then(response => response.json())
        .then(data => {
            var responsemessage = data.response;
            alert(responsemessage);

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

function toggleAllCartCheckbox(className){

    var checkBoxes = document.querySelectorAll('.' + className);
    thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
    alert(thisBoxChecked);
            for(var i = 0; i < checkBoxes.length; i++){
                checkBoxes[i].checked = thisBoxChecked;
            }
}

function calculateItemPriceSum(cartDTOList){

}