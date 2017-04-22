package com.yiwen.guet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import com.yiwen.guet.R;
import com.yiwen.guet.db.Course;
import com.yiwen.guet.service.CourseService;
import com.yiwen.guet.utils.CommonUtil;
import java.util.List;


/**
 * @author lizhangqu
 * @date 2015-2-1
 */
public class CourseActivity extends Activity {
	//课程页面的button引用，6行7列
	private int[][] lessons={
			{R.id.lesson11,R.id.lesson12,R.id.lesson13,R.id.lesson14,R.id.lesson15,R.id.lesson16,R.id.lesson17},
			{R.id.lesson21,R.id.lesson22,R.id.lesson23,R.id.lesson24,R.id.lesson25,R.id.lesson26,R.id.lesson27},
			{R.id.lesson31,R.id.lesson32,R.id.lesson33,R.id.lesson34,R.id.lesson35,R.id.lesson36,R.id.lesson37},
			{R.id.lesson41,R.id.lesson42,R.id.lesson43,R.id.lesson44,R.id.lesson45,R.id.lesson46,R.id.lesson47},
			{R.id.lesson51,R.id.lesson52,R.id.lesson53,R.id.lesson54,R.id.lesson55,R.id.lesson56,R.id.lesson57},
			{R.id.lesson61,R.id.lesson62,R.id.lesson63,R.id.lesson64,R.id.lesson65,R.id.lesson66,R.id.lesson67},
	};
	//某节课的背景图,用于随机获取
	private int[] bg={R.drawable.kb1,R.drawable.kb2,R.drawable.kb3,R.drawable.kb4,R.drawable.kb5,R.drawable.kb6,R.drawable.kb7};
	private CourseService courseService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		initValue();
		initView();
	}

	/**
	 * 初始化变量
	 */
	private void initValue() {
		courseService=CourseService.getCourseService();
	}
	/**
	 * 初始化视图
	 */
	private void initView() {
		//这里有逻辑问题，只是简单的显示了下数据，数据并不一定是显示在正确位置
		//课程可能有重叠
		//课程可能有1节课的，2节课的，3节课的，因此这里应该改成在自定义View上显示更合理
		List<Course> courses=courseService.findAll();//获得数据库中的课程
		Course course=null;
		//循环遍历
		for (int i = 0; i < courses.size(); i++) {
			course=courses.get(i);//拿到当前课程
			int dayOfWeek = course.getDayOfWeek()-1;//转换为lessons数组对应的下标
			int section=course.getStartSection()/2;//转换为lessons数组对应的下标
			Button lesson=(Button)findViewById(lessons[section][dayOfWeek]);//获得该节课的button
			int bgRes=bg[CommonUtil.getRandom(bg.length-1)];//随机获取背景色
			lesson.setBackgroundResource(bgRes);//设置背景
			lesson.setText(course.getCourseName()+"@"+course.getClasssroom());//设置文本为课程名+“@”+教室
		}
	}
}
