package mvc.domain;

public class setPage {
	private int pageIndex;
	private int pageCount;
	private int viewPage;
	private int pageBlock;
	
	public setPage(int viewPage, int pageIndex, int pageCount, int pageBlock) {
		super();
		this.viewPage = viewPage;
		this.pageIndex = pageIndex;
		this.pageCount = pageCount;
		this.pageBlock = pageBlock;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getViewPage() {
		return viewPage;
	}

	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	
	
	
}
