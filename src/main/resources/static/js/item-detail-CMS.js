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

function checkNonMemberItemStockPurchase(itemNo, itemName, itemPrice){
    alert('checkNonMemberItemStockPurchase 진입');

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

    alert('itemNo : ' + itemNo);
    alert('itemName : ' + itemName);
    alert('itemPrice : ' + itemPrice);
    alert('itemSize : ' + itemSize);
    alert('itemQuantity : ' + itemQuantity);

    //let jsonItem = {itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};

    jsonData.push({itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice});

    // fetch(`/carts/${memberNo}`) {}

    fetch(`/orders/check-itemstock`, {
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
