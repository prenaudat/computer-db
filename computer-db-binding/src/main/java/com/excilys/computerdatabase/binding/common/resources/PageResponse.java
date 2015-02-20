package com.excilys.computerdatabase.binding.common.resources;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.excilys.computerdatabase.core.common.RequestPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Utilisty class for marshalling result page by jackson.
 * 
 * @author excilys
 *
 * @param <T>
 *            Page Type
 */
public class PageResponse<T> implements Page<T> {

	protected int number;
	protected int size;
	protected int totalPages;
	protected int numberOfElements;
	protected long totalElements;
	protected List<T> content;

	/**
	 * Protected constructor
	 */
	protected PageResponse() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getContent()
	 */
	@Override
	@XmlElementWrapper(name = "content")
	@XmlElement(name = "content")
	@JsonView(View.class)
	public List<T> getContent() {
		return content;
	}

	/**
	 * Set content
	 * 
	 * @param content
	 *            List of elements
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getNumber()
	 */
	@Override
	@JsonView(View.class)
	public int getNumber() {
		return number;
	}

	/**
	 * Set page number
	 * 
	 * @param number
	 *            The page number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getNumberOfElements()
	 */
	@Override
	@JsonView(View.class)
	public int getNumberOfElements() {
		return numberOfElements;
	}

	/**
	 * Set numberOfElements
	 * 
	 * @param numberOfElements
	 *            total number of elements on page
	 */
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getSize()
	 */
	@Override
	@JsonView(View.class)
	public int getSize() {
		return size;
	}

	/**
	 * Set size
	 * 
	 * @param size
	 *            the size of the page
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	@Override
	@JsonView(View.class)
	public long getTotalElements() {
		return totalElements;
	}

	/**
	 * Return total number of elements
	 * 
	 * @param totalElements
	 *            count of items
	 */
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Page#getTotalPages()
	 */
	@Override
	@JsonView(View.class)
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Set total number of pages
	 * 
	 * @param totalPages
	 *            number of total elements in memory
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#nextPageable()
	 */
	@Override
	public Pageable nextPageable() {
		return new RequestPage(number + 1, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#previousPageable()
	 */
	@Override
	public Pageable previousPageable() {
		return new RequestPage(number - 1, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	@JsonIgnore
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasContent()
	 */
	@Override
	@JsonIgnore
	public boolean hasContent() {
		return (content != null) && (!content.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#getSort()
	 */
	@Override
	@JsonIgnore
	public Sort getSort() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasPrevious()
	 */
	@Override
	@JsonIgnore
	public boolean hasPrevious() {
		return number > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#hasNext()
	 */
	@Override
	@JsonIgnore
	public boolean hasNext() {
		return number < totalPages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#isFirst()
	 */
	@Override
	@JsonIgnore
	public boolean isFirst() {
		return number == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Slice#isLast()
	 */
	@Override
	@JsonIgnore
	public boolean isLast() {
		return number == totalPages;
	}

	/**
	 * View interface
	 * 
	 * @author excilys
	 *
	 */
	public interface View {
	}

}
