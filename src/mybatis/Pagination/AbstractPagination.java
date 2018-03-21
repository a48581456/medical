package mybatis.Pagination;

import org.apache.ibatis.session.RowBounds;

public abstract class AbstractPagination extends RowBounds implements Pagination {

	private boolean totol = true;
	private boolean offset = true;
	private boolean limit = true;
	private int count;
	private int startRow;
	private int endRow;
	private int pageSize = 9;
	private int page = 1;
	private int pageCount;
	
	public void setTotol(boolean totol) {
		this.totol = totol;
	}

	@Override
	public boolean isTotol() {
		return totol;
	}

	public void setOffset(boolean offset) {
		this.offset = offset;
	}
	
	@Override
	public boolean isOffset() {
		return offset;
	}

	@Override
	public boolean isLimit() {
		return limit;
	}
	
	public void setLimit(boolean limit) {
		this.limit = limit;
	}

	@Override
	public void setCount(int count) {
		if (count < 0) {
			this.count = 0;
			this.pageCount = 0;
		} else {
			this.count = count;
			setPageCount(this.pageCount);
		}
	}

	@Override
	public int getCount() {
		return count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		
		if (page <= 0) {
			setOffset(false);
			setLimit(false);
			this.startRow = 0;
			this.endRow = 0;
		} else {
			this.startRow = (page - 1) * this.pageSize + 1;
			this.endRow = page * this.pageSize;
			setOffset(true);
			setLimit(true);
		}
		
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		if (this.count%this.pageSize == 0) {
			this.pageCount = this.count / this.pageSize;
		} else {
			this.pageCount = this.count / this.pageSize + 1;
		}
	}

	public void setStartRow(int startRow) {
		if (startRow < 0) {
			setOffset(false);
			this.startRow = 0;
		} else {
			this.startRow = startRow;
			setOffset(true);
		}
	}

	@Override
	public int getStartRow() {
		
		this.setPage(page);
		
		return startRow;
	}

	public void setEndRow(int endRow) {
		if (endRow < 0) {
			setLimit(false);
			this.endRow = 0;
		} else {
			this.endRow = endRow;
			setLimit(true);
		}
	}

	@Override
	public int getEndRow() {
		
		this.setPage(page);
		
		return endRow;
	}

}
