package com.sort;

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextMembershipListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

public class SelfContent  implements BeanContext{

	@Override
	public void setBeanContext(BeanContext bc) throws PropertyVetoException {
		
	}

	@Override
	public BeanContext getBeanContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPropertyChangeListener(String name,
			PropertyChangeListener pcl) {
		
	}

	@Override
	public void removePropertyChangeListener(String name,
			PropertyChangeListener pcl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVetoableChangeListener(String name,
			VetoableChangeListener vcl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVetoableChangeListener(String name,
			VetoableChangeListener vcl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDesignTime(boolean designTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDesignTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean needsGui() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dontUseGui() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void okToUseGui() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean avoidingGui() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object instantiateChild(String beanName) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getResourceAsStream(String name, BeanContextChild bcc)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getResource(String name, BeanContextChild bcc)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBeanContextMembershipListener(
			BeanContextMembershipListener bcml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBeanContextMembershipListener(
			BeanContextMembershipListener bcml) {
		// TODO Auto-generated method stub
		
	}

}
