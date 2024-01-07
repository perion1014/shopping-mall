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
    //alert('checkNonMemberItemStockPurchase 진입');

    let jsonData= [];

    let itemSize = '';
    let itemQuantity = 0;
    let itemThumbnail = document.getElementById('item_Thumbnail').src.replace('http://localhost:8082', '');
    //alert("상품 상세정보에서 받아온 썸네일 : " + itemThumbnail);

    let itemSizelist = document.getElementsByName(`itemSize`);

    for (var i=0; i <itemSizelist.length; i++) {
        if (itemSizelist[i].checked) {
            itemSize = itemSizelist[i].value;
        }
    }

    itemQuantity = document.getElementById(`item__count`).value;
    let isPurchaseInItemDetailPage = true;

    // alert('itemNo : ' + itemNo);
    // alert('itemName : ' + itemName);
    // alert('itemPrice : ' + itemPrice);
    // alert('itemSize : ' + itemSize)  ;
    // alert('itemQuantity : ' + itemQuantity);

    jsonData.push({itemNo: itemNo,
                   itemName: itemName,
                   itemSize: itemSize,
                   itemQuantity: itemQuantity,
                   itemPrice: itemPrice,
                   itemThumbnail:itemThumbnail,
                   isPurchaseInItemDetailPage: isPurchaseInItemDetailPage});

    fetch(`/orders/check-itemstock`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify(jsonData)
            }).then(response => response.json())
                .then(data => {

                    //alert('응답 결과 : ' + JSON.stringify(data.response).replaceAll('"', ''));

                    if(JSON.stringify(data.response).replaceAll('"', '') !== '데이터 전달/처리 성공'){
                        var stocksArray = JSON.stringify(data.response).split(',');
                        var noStockMessage = '';

                        for(var i = 0; i < stocksArray.length; i++){
                            noStockMessage += stocksArray[i] + '\n';
                        }

                        alert(noStockMessage.replaceAll('"', '') + '상품의 재고가 부족합니다.');

                        // if(JSON.stringify(data.response).replaceAll('"', '') === '선택하신 상품의 재고가 없습니다.'){
                        //     alert(JSON.stringify(data.response));
                        //     location.reload();
                    } else {
                        location.href = '/carts';
                    }

        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    })
}
