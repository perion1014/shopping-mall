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

            if(JSON.stringify(data.response).replaceAll('"', '') === '선택하신 상품의 재고가 없습니다.'){
                alert(JSON.stringify(data.response));
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

function checkItemStockPurchase(memberNo, itemNo, itemName, itemPrice){
    // alert('온클릭 진입');

    let jsonData= [];

    let itemSize = '';
    let itemQuantity = 0;

    let itemSizelist = document.getElementsByName(`itemSize`);

    for (var i=0; i <itemSizelist.length; i++) {
        if (itemSizelist.item(i) !== null) {
            itemSize = itemSizelist.item(i).value;
        }
    }

    itemQuantity = document.getElementById(`item__count`).value;

    let jsonItem = {memberNo: memberNo, itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};

    jsonData.push(jsonItem);


    // fetch(`/carts/${memberNo}`) {}

    fetch(`/members/${memberNo}/orders/check-itemstock`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            if(JSON.stringify(data.response).replaceAll('"', '') === '선택하신 상품의 재고가 없습니다.'){
                alert(JSON.stringify(data.response));
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


