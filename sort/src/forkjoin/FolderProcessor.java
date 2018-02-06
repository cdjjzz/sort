package forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
/**
 * 主线程提交任务给子线程 ，等待子线程处理结果，主线程统计结果
 * @author pet-lsf
 *
 */
public class FolderProcessor extends RecursiveTask<List<String>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String path;
	
	private String extension;
	
	public FolderProcessor() {
	}
	
	public FolderProcessor(String path,String extension){
		this.path=path;
		this.extension=extension;
	}

	@Override
	protected List<String> compute() {
		//文件夹名称
		List<String> list=new  ArrayList<String>();
		//递归子任务
		List<FolderProcessor> childProcessor=new ArrayList<FolderProcessor>();
		File file=new File(path);
		//获取file下面的子文件
		File[] f=file.listFiles();
		if(f!=null){
			for (int i = 0; i < f.length; i++) {
				if(f[i].isDirectory()){//目录
					FolderProcessor processor=new FolderProcessor(f[i].getAbsolutePath(), extension);
					processor.fork();//线程处理
					childProcessor.add(processor);
				}else{
					if(checkFile(f[i].getName())){
						list.add(f[i].getAbsolutePath());
					}
				}
			}
		}
		if (childProcessor.size()>50) {
					System.out.printf("%s: %d tasks ran.\n",file.
						getAbsolutePath(),childProcessor.size());
		}
		addResultsFromTasks(list, childProcessor);
		return list;
	}
	private boolean checkFile(String fileName){
		return fileName.endsWith(extension);
	}
	
	private void addResultsFromTasks(List<String> lists,List<FolderProcessor> folderProcessors){
		for (FolderProcessor folderProcessor:folderProcessors) {
			lists.addAll(folderProcessor.join());//获取结果
		}
	}
}
