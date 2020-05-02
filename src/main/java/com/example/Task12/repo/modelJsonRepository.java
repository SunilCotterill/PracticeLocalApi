package com.example.Task12.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Task12.model.modelJson;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

//Creating queries for modelJson class
public interface modelJsonRepository extends JpaRepository <modelJson, Integer> {
	List<modelJson> findByAppCode(int appCode);
	
	List<modelJson> findByVersion(double version);
	
	
	

	
	//complex queries	
	
	//returns a list of all modelJsons with a specified appCode in descending order of dateModified 
	@Query("from modelJson where appCode=?1 order by dateModified desc")
	public List<modelJson> findByAppCodeSorted (int appCode);
	
	
	
	//returns a list of all modelJsons with a specified appCode and version
	@Query("from modelJson where appCode=?1 and version=?2")
	List <modelJson> findByAppCodeVersion(int appCode, double version);
	
	
}
