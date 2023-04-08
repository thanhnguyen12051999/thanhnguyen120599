package service;

import java.util.List;
import bean.Ear;

public interface EarService {

public List<Ear> search(String keyword);
	
	public boolean insertEar(Ear ear);
	
	public boolean updateEar(Ear ear);
	
	public boolean deleteEar(int earId);
	
	public Ear selectEar(int earId);
}
