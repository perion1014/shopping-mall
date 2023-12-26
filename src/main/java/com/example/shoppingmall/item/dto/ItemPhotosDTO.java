package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.ItemPhotos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPhotosDTO {

    private Long itemNo;
    private String itemThumb;
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    private String itemThumbModified;
    private String itemImg1Modified;
    private String itemImg2Modified;
    private String itemImg3Modified;

    public static ItemPhotosDTO toItemPhotosDTO(ItemPhotos itemPhotos) {
        ItemPhotosDTO itemPhotosDTO = new ItemPhotosDTO();
        itemPhotosDTO.setItemNo(itemPhotos.getItemNo());
        itemPhotosDTO.setItemThumb(itemPhotos.getItemThumb());
        itemPhotosDTO.setItemImg1(itemPhotos.getItemImg1());
        itemPhotosDTO.setItemImg2(itemPhotos.getItemImg2());
        itemPhotosDTO.setItemImg3(itemPhotos.getItemImg3());
        itemPhotosDTO.setItemImg1Modified(itemPhotos.getItemImg1Modified());
        itemPhotosDTO.setItemImg2Modified(itemPhotos.getItemImg2Modified());
        itemPhotosDTO.setItemImg3Modified(itemPhotos.getItemImg3Modified());
        return itemPhotosDTO;
    }

}
