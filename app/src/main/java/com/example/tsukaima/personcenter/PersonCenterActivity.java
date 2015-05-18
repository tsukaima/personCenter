package com.example.tsukaima.personcenter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;


public class PersonCenterActivity extends ActionBarActivity implements ScrollViewListener {

    //private Userprofile userprofile = null;

    /**
     * 前台所有的模块
     */
    private Toolbar toolbar = null;
    private ObservableScrollView scrollView = null;
    private LinearLayout headLinearLayout = null;
    private LinearLayout informationLinearLayout = null;
    private LinearLayout tagLinearLayout = null;
    private LinearLayout phoneLinearLayout = null;
    private LinearLayout emailLinearLayout = null;
    private LinearLayout educationLinearLayout = null;
    private LinearLayout workLinearLayout = null;
    private LinearLayout awardLinearLayout = null;
    private LinearLayout skillLinearLayout = null;

    private int visitor = 0; // TODO 访问用户类型 未设定
    private boolean isEdit = true; //判断是否可编辑
    private boolean isFirst = true; //判断是否首次加载
    private int toolbarHeight = 0; //Toolbar高度
    private boolean isScrollBelow = false; //判断是否滚动到底下


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);

        this.initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person_center, menu);
        menu.findItem(R.id.action_edit).setIcon(
                new IconDrawable(this, Iconify.IconValue.fa_pencil).colorRes(R.color.white)
                        .actionBarSize());
        menu.findItem(R.id.action_share).setIcon(
                new IconDrawable(this, Iconify.IconValue.fa_share).colorRes(R.color.white)
                        .actionBarSize());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 初始化函数
     */
    private void initView() {
        this.informationLinearLayout = (LinearLayout) findViewById(R.id.detailed_information);
        this.setScrollView();
        this.setToolbar();
        this.setHead();
        this.setTag();
        this.setPhone();
        this.setEmail();
        this.setEducation();
        this.setWork();
        this.setAward();
        this.setSkill();
    }

    /**
     * 设置ScrollView
     */
    private void setScrollView() {
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        scrollView.setScrollViewListener(this);
    }

    /**
     * 设置Toolbar
     */
    private void setToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
        toolbar.setNavigationIcon(new IconDrawable(this, Iconify.IconValue.fa_navicon)
                .colorRes(R.color.white).actionBarSize());
        toolbar.setBackgroundColor(getResources().getColor(R.color.person_toolbar));
        toolbar.getBackground().setAlpha(0);
    }

    /**
     * 设置头部
     */
    private void setHead() {
        this.headLinearLayout = (LinearLayout) findViewById(R.id.information_head);
        this.headLinearLayout.setBackgroundResource(R.drawable.layoutbackground);

        TextView usernameTextView = (TextView) findViewById(R.id.username);
        TextView schoolMajorTextView = (TextView) findViewById(R.id.school_major);
        TextView nowStatusTextView = (TextView) findViewById(R.id.now_status);

        usernameTextView.setText("汪海洋");
        schoolMajorTextView.setText("北京邮电大学测试工程");
        nowStatusTextView.setText("奇虎360测试工程师");

        if(this.isEdit) {
            LayoutInflater inflater = LayoutInflater.from(this);
            TextView editTextView = (TextView) inflater.inflate(R.layout.person_edit_textview, null);

            editTextView.setText("点击编辑");
            editTextView.setGravity(Gravity.CENTER);

            LinearLayout headIconLinearLayout = (LinearLayout) findViewById(R.id.information_head_icon);
            headIconLinearLayout.addView(editTextView);
        }
    }

    /**
     * 设置标签模块
     */
    private void setTag() {
        this.tagLinearLayout = getPersonProfileItem("{fa-user}", null, false);
        LinearLayout tagContent = (LinearLayout) this.tagLinearLayout.findViewById(R.id.content);
        //TODO 这里添加标签内容到tagContent里面去

        this.informationLinearLayout.addView(this.tagLinearLayout);
    }

    /**
     * 设置电话模块
     */
    private void setPhone() {
        this.phoneLinearLayout = getPersonProfileItem("{fa-phone}", "点击编辑", this.isEdit);
        LinearLayout phoneContent = (LinearLayout) this.phoneLinearLayout.findViewById(R.id.content);

        //新建TextView存放电话文本
        LayoutInflater inflater = LayoutInflater.from(this);
        TextView phoneTextView = (TextView) inflater.inflate(R.layout.person_simple_textview, null);

        phoneTextView.setText("(650-555-1234)");

        phoneContent.addView(phoneTextView);
        this.informationLinearLayout.addView(this.phoneLinearLayout);
    }

    /**
     * 设置邮件模块
     */
    private void setEmail() {
        this.emailLinearLayout = getPersonProfileItem("{fa-envelope-o}", "点击编辑", this.isEdit);
        LinearLayout emailContent = (LinearLayout) this.emailLinearLayout.findViewById(R.id.content);

        //新建TextView存放邮件地址
        LayoutInflater inflater = LayoutInflater.from(this);
        TextView emailTextView = (TextView) inflater.inflate(R.layout.person_simple_textview, null);

        emailTextView.setText("qcystudio@qq.com");

        emailContent.addView(emailTextView);
        this.informationLinearLayout.addView(this.emailLinearLayout);
    }

    /**
     * 设置教育背景模块
     */
    private void setEducation() {
        this.educationLinearLayout = getPersonProfileItem("{fa-mortar-board}", "新增教育经历", this.isEdit);
        LinearLayout educationList = (LinearLayout) this.educationLinearLayout.findViewById(R.id.content);

        LayoutInflater inflater = LayoutInflater.from(this);
        //内容的每一项以及每一项里的内容
        LinearLayout educationItem;
        TextView schoolTextView;
        TextView degreeMajorTextView;
        TextView timeTextView;

        //TODO
        educationItem = (LinearLayout) inflater.inflate(R.layout.edu_list_item, null);
        schoolTextView = (TextView) educationItem.findViewById(R.id.school);
        degreeMajorTextView = (TextView) educationItem.findViewById(R.id.degree_major);
        timeTextView = (TextView) educationItem.findViewById(R.id.time);

        schoolTextView.setText("北京邮电大学");
        degreeMajorTextView.setText("本科-软件工程");
        timeTextView.setText("2002.7-2006.8");
        educationItem.setPadding(0, 0, 0, 20);
        educationList.addView(educationItem); //TODO 这里需要用到for循环

        //TODO
        educationItem = (LinearLayout) inflater.inflate(R.layout.edu_list_item, null);
        schoolTextView = (TextView) educationItem.findViewById(R.id.school);
        degreeMajorTextView = (TextView) educationItem.findViewById(R.id.degree_major);
        timeTextView = (TextView) educationItem.findViewById(R.id.time);

        schoolTextView.setText("北京邮电大学");
        degreeMajorTextView.setText("本科-软件工程");
        timeTextView.setText("2002.7-2006.8");
        educationItem.setPadding(0, 0, 0, 20);
        educationList.addView(educationItem); //TODO 这里需要用到for循环

        this.informationLinearLayout.addView(this.educationLinearLayout);
    }

    /**
     * 设置工作经历
     */
    private void setWork() {
        this.workLinearLayout = getPersonProfileItem("{fa-suitcase}", "新增项目和实习经历", this.isEdit);
        LinearLayout workList = (LinearLayout) this.workLinearLayout.findViewById(R.id.content);

        LayoutInflater inflater = LayoutInflater.from(this);
        //内容的每一项以及每一项里的内容
        LinearLayout workItem;
        TextView positionTextView;
        TextView timeTextView;
        TextView companyTextView;

        //TODO
        workItem = (LinearLayout) inflater.inflate(R.layout.work_list_item, null);
        positionTextView = (TextView) workItem.findViewById(R.id.position);
        timeTextView = (TextView) workItem.findViewById(R.id.time);
        companyTextView = (TextView) workItem.findViewById(R.id.company);

        positionTextView.setText("经理");
        timeTextView.setText("2002.7-2006.8");
        companyTextView.setText("北京职圈科技有限公司");
        workItem.setPadding(0, 0, 0, 20);
        workList.addView(workItem);// TODO 这里需要用到for循环

        this.informationLinearLayout.addView(this.workLinearLayout);
    }

    /**
     * 设置获奖情况
     */
    private void setAward() {
        this.awardLinearLayout = getPersonProfileItem("{fa-trophy}", "新增奖励", this.isEdit);
        LinearLayout awardList = (LinearLayout) this.awardLinearLayout.findViewById(R.id.content);

        LayoutInflater inflater = LayoutInflater.from(this);
        //内容的每一项
        TextView awardItem;

        //TODO
        awardItem = (TextView) inflater.inflate(R.layout.person_simple_textview, null);
        awardItem.setText("在校期间获得全国大学生竞赛奖");
        awardList.addView(awardItem); //TODO 这里需要用到for循环

        //TODO
        awardItem = (TextView) inflater.inflate(R.layout.person_simple_textview, null);
        awardItem.setText("在校期间获得全国大学生竞赛奖");
        awardList.addView(awardItem); //TODO 这里需要用到for循环

        this.informationLinearLayout.addView(this.awardLinearLayout);
    }

    /**
     * 设置技能
     */
    private void setSkill() {
        this.skillLinearLayout = getPersonProfileItem("{fa-certificate}", "新增技能", this.isEdit);
        LinearLayout skillList = (LinearLayout) this.skillLinearLayout.findViewById(R.id.content);

        LayoutInflater inflater = LayoutInflater.from(this);
        //内容的每一项
        TextView skillItem;

        //TODO
        skillItem = (TextView) inflater.inflate(R.layout.person_simple_textview, null);
        skillItem.setText("英语6级");
        skillList.addView(skillItem); //TODO 这里需要用到for循环

        //TODO
        skillItem = (TextView) inflater.inflate(R.layout.person_simple_textview, null);
        skillItem.setText("计算机二级");
        skillList.addView(skillItem); //TODO 这里需要用到for循环

        this.informationLinearLayout.addView(this.skillLinearLayout);
    }

    /**
     * 获得一个新的资料模块
     *
     * @param icon   图标
     * @param edit   编辑按钮的内容
     * @param isEdit 是否可编辑
     * @return
     */
    private LinearLayout getPersonProfileItem(String icon, String edit, boolean isEdit) {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.person_profile_item, null);

        //设置模块图标
        TextView iconTextView = (TextView) linearLayout.findViewById(R.id.icon);
        iconTextView.setText(icon);

        //模块是否添加编辑按钮
        if (isEdit) {
            LinearLayout detailsLinearLayout = (LinearLayout) linearLayout.findViewById(R.id.information_details);
            TextView editTextView = (TextView) inflater.inflate(R.layout.person_edit_textview, null);
            editTextView.setText(edit);
            detailsLinearLayout.addView(editTextView, 1);
        }

        return linearLayout;
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        /**
         * 滑动改变透明度
         */
        if (y <= 0) {
            toolbar.getBackground().setAlpha(0);
            this.isScrollBelow = false;
        } else if (y >= this.toolbarHeight) {
            if(!this.isScrollBelow) {
                toolbar.getBackground().setAlpha(255);
                this.isScrollBelow = true;
            }
        } else {
            int alpha = y * 255 / this.toolbarHeight;
            toolbar.getBackground().setAlpha(alpha);
            this.isScrollBelow = false;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        /**
         * 判断是否首次加载
         * 获取Toolbar高度
         */
        if (hasFocus && isFirst) {
            //do something
            isFirst = false;
            toolbarHeight = toolbar.getHeight();
        }
    }
}
