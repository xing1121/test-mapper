package com.sf.wdx.start;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.sf.wdx.domain.Person;
import com.sf.wdx.mapper.PersonMapper;

public class StartUp {
	
	/**
	 * 测试mybatis的通用mapper插件，只需要实现Mapper<T>接口
	 *	@ReturnType	void 
	 *	@Date	2018年8月13日	上午11:55:02
	 *  @Param  @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-beans.xml");
		
		// 查询所有
		PersonMapper personMapper = context.getBean(PersonMapper.class);
		List<Person> list = personMapper.selectByExample(null);
		list.forEach(System.out::println);
		System.out.println("-------------------------------------");
		
		// 删除数据
		Example example = new Example(Person.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("name", "田七");
		personMapper.deleteByExample(example);
		System.out.println("删除成功！");
		System.out.println("-------------------------------------");
		
		// 插入数据
		Person p = new Person(null, "田七", 50, "tianqi@qq.com");
		personMapper.insert(p);
		System.out.println("插入成功！");
		System.out.println("-------------------------------------");
		
		// 更新数据
		p.setEmail("abcdefg@hijk.com");
		personMapper.updateByExampleSelective(p, example);
		System.out.println("更新成功！");
		System.out.println("-------------------------------------");
		
		// 按照id更新非空字段
		Person pp = new Person();
		pp.setId(21L);
		pp.setAge(123);
		personMapper.updateByPrimaryKeySelective(pp);
		
		// 条件查询
		list = personMapper.selectByExample(example);
		list.forEach(System.out::println);
		
	}
	
}
