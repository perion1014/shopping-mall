//장바구니 상품 선택하고 구매 누를 시, 구매 페이지로 넘어가기 전 재고 체크 함수
function checkItemStock2(cartDTOListSize, memberNo){


    let jsonData= [];

    let itemNo = 0;
    let itemName = '';
    let itemSize = '';
    let itemQuantity = 0;
    let itemPrice = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        // alert(`i: ${i}`);
        if(document.getElementById(`cartCheckBox_${i}`).checked === true){
            itemNo = document.getElementById(`itemNo_${i}`).value;
            itemName = document.getElementById(`itemName_${i}`).innerText;
            itemSize = document.getElementById(`itemSize_${i}`).innerText;
            itemQuantity = document.getElementById(`inputvalue_${i}`).value;
            itemPrice = document.getElementById(`itemPrice_${i}`).value;

            let jsonItem = {itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};
            jsonData.push(jsonItem);
        }
    }

    fetch(`/members/${memberNo}/orders/check-itemstock`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            // alert(JSON.stringify(data.response));

            if(JSON.stringify(data.response) === '선택하신 상품의 재고가 없습니다.'){
                alert('aaa');
                location.reload();
            } else {
                fetch(`/members/${memberNo}/orders/create`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(jsonData)
                }).then( data => {
                    location.href = '/members/orders/create';
                })
            }

        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    })
}