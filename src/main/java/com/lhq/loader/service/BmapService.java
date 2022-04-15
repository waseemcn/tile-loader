package com.lhq.loader.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lhq.loader.bean.LngLat;
import com.lhq.loader.bean.Tile;
import com.lhq.loader.commons.toolkit.CoordinateToolkit;
import com.lhq.loader.controller.vo.DownloadParamVO;

/**
 * 百度地图下载器
 * 
 * @author lhq
 *
 */
@Service("bmapService")
public class BmapService implements IMapService {
    @Value("${config.baseUrl.bmapapi}")
    private String baseUrl;

    /**
     * 计算下载的瓦片数量
     * 
     */
    @Override
    public long calculateCount(DownloadParamVO downloadParamVO) {
        return MapServiceToolkit.calcTileCount(downloadParamVO, (LngLat lngLat, int zoom, Tile tile) -> CoordinateToolkit.bd09_LngLat_To_Tile(lngLat, zoom, tile));
    }

    /**
     * 开始下载瓦片
     * 
     */
    @Override
    public void startDownload(DownloadParamVO downloadParamVO) {
        MapServiceToolkit.addDownTask(downloadParamVO, baseUrl, this, (LngLat lngLat, int zoom, Tile tile) -> CoordinateToolkit.bd09_LngLat_To_Tile(lngLat, zoom, tile));
    }

}
