package com.yzd.collegecommunity.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Laiyin on 2017/4/4.
 */

public class SelectImageUtil {

    private ImageView mImage;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
    private Activity activity;

    private SubscriberOnNextListener mListener;

    public SelectImageUtil(Activity activity, ImageView mImage) {
        this.activity = activity;
        this.mImage = mImage;
    }

    /**
     * 重写onActivityResult
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, CROP_SMALL_PICTURE);
    }


    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            //显示图片,这里图片是方形的，可以用一个工具类处理成圆形
            mImage.setImageBitmap(mBitmap);
            //在这个地方可以写上上传该图片到服务器的代码
            Bitmap2Bytes(mBitmap);

            if(mOnSelectImageOptionListener != null){
                mOnSelectImageOptionListener.onChoosePhoto();
            }
//            Map<String, ResponseBody> bodyMap = new HashMap<>();
//            bodyMap.put("file"+"\";filename=\""+file.getName(),ResponseBody.create(MediaType.parse("image/png"),file));
//
//            mListener = new SubscriberOnNextListener() {
//                @Override
//                public void onNext(Object o) {
//                    ToastUtil.showShort(AppCenterUtil.getContextObject(), "Upload Success");
//                }
//            };
//            RetrofitUtil.getInstance().uploadSingleFile(etUsername.getText().toString(), SPUtil.getToken(),
//                    new ProgressSubscriber<HttpWrapper<String>>(mListener, AppCenterUtil.getContextObject()));
        }
    }

    public OnSelectImageOptionListener mOnSelectImageOptionListener;

    public void setOnSelectImageOptionListener(OnSelectImageOptionListener listener){
        mOnSelectImageOptionListener = listener;
    }

    public interface OnSelectImageOptionListener{
        void onTakePhoto();
        void onChoosePhoto();
    }

    /**
     * 把Bitmap转Byte
     * @param bitmap
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }

    /**
     * 选择本地照片
     */
    public void choosePicture() {
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
        activity.startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
    }

    /**
     * 拍照
     */
    public void takePicture() {
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        tempUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "temp_image.jpg"));
        // 将拍照所得的相片保存到SD卡根目录
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        activity.startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }
}
