package com.yzd.collegecommunity.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MainFragmentTaskListAdapter;
import com.yzd.collegecommunity.modeal.MainTaskListInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laiyin on 2017/3/5.
 */


public class Main_TaskFragment extends Fragment {

    private MainFragmentTaskListAdapter mainFragmentTaskListAdapter;
    private ListView lv_task;

    private List<MainTaskListInfo> mainTaskListInfoList=new ArrayList<MainTaskListInfo>();

    private Activity mContext;

    private Bitmap bitmap;
    private Bitmap bitmapHead;
    private String introduce;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment_task,container,false);

        initView(view);

        initListener();

//        addList();

        return view;
    }

    private void initView(View view) {
        lv_task= (ListView) view.findViewById(R.id.lv_task);
        mainFragmentTaskListAdapter=new MainFragmentTaskListAdapter(this.getActivity(),R.layout.main_fragment_task_item,mainTaskListInfoList);
        lv_task.setAdapter(mainFragmentTaskListAdapter);
    }

    private void initListener() {
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainTaskListInfo mainTaskListInfo=mainTaskListInfoList.get(i);
//                Intent intent=new Intent(mContext,mission.class);
//                intent.putExtras();
//                startActivity(intent);
            }
        });
    }

    private void addList() {

//        my_mm_v1_List.clear();
////        taskList.addWhereEqualTo("mission_username",mypublic_user);
//        taskList.order("-createdAt");
//        taskList.findObjects(new FindListener<TaskMission>() {
//            @Override
//            public void done(List<TaskMission> list, BmobException e) {
//                if (list!=null&&e==null){
//                    Log.e("done: ",list.size()+"" );
//                    for(TaskMission mm:list) {
//                        Mm_v1 taskdecribe = new Mm_v1();
//                        taskdecribe.setIntroduce(mm.getDescription());
//                        taskdecribe.setPicUrl(mm.getPicture());
//                        my_mm_v1_List.add(taskdecribe);
//                    }
//                    mListViewAdapter.notifyDataSetChanged();
//                }
//            }
//        });

//    MainTaskListInfo mainTaskListInfo=new MainTaskListInfo(,,,);
//    lv_task.add(mainTaskListInfo);
    }

    private void getData(){
//        //头像，下载
//        OkHttpUtils
//                .get()
//                .url(Constants.BASEURL+"download")
//                .build()
//                .execute(new BitmapCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
////                               password.setText("onError:"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(Bitmap response, int id) {
//
//                        bitmapHead=response;
//
//                    }
//                });
//
//        //描述下载
//
//
//    OkHttpUtils
//                .post()
//                .url(Constants.BASEURL+"toTaskList.action")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//
//                        final String res=response;
//
////                        List<MainTaskListInfo> m = response.fromJson(res, new TypeToken<List<MainTaskListInfo>>(){}.getType());
////                        for(int i =0; i < m.size() ; i++)
////                        {
////                            MainTaskListInfo mt = m.get(i);
////                            System.out.println(mt.toString());
////                        }
//
//                        System.out.println(res);
//                    }
//                });


//        HttpsUtils httpsUtils=new HttpsUtils();
//        httpsUtils.getJsonObject();
    }

//    private Bitmap bitmap;
//    //图片下载
//    private Bitmap downloadFile(final BmobFile file, final int i) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bitmap=null;
//                //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
//                final File saveFile = new File(getActivity().getApplicationContext().getCacheDir(), file.getFilename());
//                file.download(saveFile, new DownloadFileListener() {
//                    @Override
//                    public void onStart() {
//
////                    toast("开始下载...");
////                Log.i(TAG, "onStart: " + file.getFileUrl());
//                    }
//
//                    @Override
//                    public void done(String savePath, BmobException e) {
//                        if (e == null) {
//                            Log.i("leilei","下载成功,保存路径:"+savePath);
//                            File f=new File(savePath);
//                            Log.e("leilei", "文件是否存在 : "+f.exists() );
//                            bitmap= BitmapFactory.decodeFile(savePath);
////                    headImg_v.setImageBitmap(bm);
//
//                            int a=bitmap.getHeight();
//                            if (bitmap!=null){
//                                Log.e("leilei", "bitmap 不为空" );
//                            }
//                            Log.e("leilei", "done: "+bitmap.getHeight() );
//                            Log.e("leilei", "getView:设置图片 ");
//                            ImageView iv= (ImageView) listView.findViewWithTag(i);
//                            iv.setImageBitmap(bitmap);
//                            //  mListViewAdapter.notifyDataSetChanged();
//
//                        } else {
////                    Log.i(TAG,e.getErrorCode()+","+e.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onProgress(Integer value, long newworkSpeed) {
////                Log.i(TAG, "下载进度：" + value + "," + newworkSpeed);
//                    }
//                });
//            }
//        }).start();
//        return bitmap;
//    }
}
