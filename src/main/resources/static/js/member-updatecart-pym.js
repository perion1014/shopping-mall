//장바구니 상품 선택하고 구매 누를 시, 구매 페이지로 넘어가기 전 재고 체크 함수
function checkItemStock2(cartDTOListSize, memberNo){

    // alert('checkItemStock2 function');
    // alert(`cartDTOListSize: ${cartDTOListSize}`);
    // alert(`memberNo: ${memberNo}`);

    let jsonData= [];

    let itemNo = 0;
    let itemName = '';
    let itemSize = '';
    let itemQuantity = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        alert(`i: ${i}`);
        if(document.getElementById(`cartCheckBox_${i}`).checked === true){
            itemNo = document.getElementById(`itemNo_${i}`).value;
            //itemName = document.getElementById('itemName_' + i).innerText;
            itemSize = document.getElementById(`itemSize_${i}`).innerText;
            itemQuantity = document.getElementById(`inputvalue_${i}`).value;
            // alert('자스에서 받아온 아이템 명 : ' + itemName);
            // alert('자스에서 받아온 아이템 사이즈 : ' + itemSize);
            // alert('자스에서 받아온 아이템 수량 : ' + selectedItemQuantity);

            alert(`itemNo: + ${itemNo}, itemSize: ${itemSize}, itemQuantity: ${itemQuantity}`);

            let jsonItem = {itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity};
            jsonData.push(jsonItem);
            // alert('아이템 명 : ' + itemName);
            // alert('아이템 사이즈 : ' + itemSize);
        }
    }

    // for(var i = 0; i < jsonData.length; i++){
    //     alert('JSON담은 이름 : ' + jsonData[i].itemName);
    //     alert('JSON담은 사이즈 : ' + jsonData[i].itemSize);
    // }

    fetch('/members/' + memberNo + '/orders/check-itemstock', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            alert(JSON.stringify(data.response));

        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    });
}