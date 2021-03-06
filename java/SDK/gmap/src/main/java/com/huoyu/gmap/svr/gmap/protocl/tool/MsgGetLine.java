package com.huoyu.gmap.svr.gmap.protocl.tool;

import com.huoyu.client.ResultCode;
import com.huoyu.gmap.svr.gmap.datastruct.Line;
import com.huoyu.gmap.svr.gmap.protocl.ProtoclNo;
import com.huoyu.gmap.svr.gsrc.constant.ServiceType;


public class MsgGetLine extends MsgPo {
    //请求参数
    public long     lineId;
    
    //回应参数
    public Line     line;
    
    public MsgGetLine()
    {
        super(10240, true, (short)ServiceType.GMAP.value());
    }

    @Override
    public boolean Build(boolean isResult) {
        if ( isResult ) return false;//不创建回应报文

        SetId((short) ProtoclNo.GET_LINE.value(), isResult);
        defaultParam();
        if ( !addInt64(lineId) ) return false;
        return true;
    }

    @Override
    public boolean Parse() {
        if ( !super.Parse() ) return false;
        if ( !isResult() ) return false;//不应该收到请求报文

        if ( ResultCode.SUCCESS.value() != code ) return true;
        
        line = getLine();
        if ( readError ) return false;
        
        return true;
    }
}
