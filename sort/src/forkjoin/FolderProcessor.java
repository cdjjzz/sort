package forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
/**
 * ���߳��ύ��������߳� ���ȴ����̴߳����������߳�ͳ�ƽ��
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
		//�ļ�������
		List<String> list=new  ArrayList<String>();
		//�ݹ�������
		List<FolderProcessor> childProcessor=new ArrayList<FolderProcessor>();
		File file=new File(path);
		//��ȡfile��������ļ�
		File[] f=file.listFiles();
		if(f!=null){
			for (int i = 0; i < f.length; i++) {
				if(f[i].isDirectory()){//Ŀ¼
					FolderProcessor processor=new FolderProcessor(f[i].getAbsolutePath(), extension);
					processor.fork();//�̴߳���
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
			lists.addAll(folderProcessor.join());//��ȡ���
		}
	}
}
