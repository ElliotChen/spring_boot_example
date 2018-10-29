package com.sportradar.sdh.dto.dts;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Collections;
import java.util.List;

public class DataTablesOutput<T> {
	@JsonView({DataTablesOutput.View.class})
	private int draw;
	@JsonView({DataTablesOutput.View.class})
	private long recordsTotal = 0L;
	@JsonView({DataTablesOutput.View.class})
	private long recordsFiltered = 0L;
	@JsonView({DataTablesOutput.View.class})
	private List<T> data = Collections.emptyList();
	@JsonView({DataTablesOutput.View.class})
	private String error;

	public DataTablesOutput() {
	}

	public int getDraw() {
		return this.draw;
	}

	public long getRecordsTotal() {
		return this.recordsTotal;
	}

	public long getRecordsFiltered() {
		return this.recordsFiltered;
	}

	public List<T> getData() {
		return this.data;
	}

	public String getError() {
		return this.error;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof DataTablesOutput)) {
			return false;
		} else {
			DataTablesOutput<?> other = (DataTablesOutput)o;
			if (!other.canEqual(this)) {
				return false;
			} else if (this.getDraw() != other.getDraw()) {
				return false;
			} else if (this.getRecordsTotal() != other.getRecordsTotal()) {
				return false;
			} else if (this.getRecordsFiltered() != other.getRecordsFiltered()) {
				return false;
			} else {
				Object this$data = this.getData();
				Object other$data = other.getData();
				if (this$data == null) {
					if (other$data != null) {
						return false;
					}
				} else if (!this$data.equals(other$data)) {
					return false;
				}

				Object this$error = this.getError();
				Object other$error = other.getError();
				if (this$error == null) {
					if (other$error != null) {
						return false;
					}
				} else if (!this$error.equals(other$error)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof DataTablesOutput;
	}

	public String toString() {
		return "DataTablesOutput(draw=" + this.getDraw() + ", recordsTotal=" + this.getRecordsTotal() + ", recordsFiltered=" + this.getRecordsFiltered() + ", data=" + this.getData() + ", error=" + this.getError() + ")";
	}

	public interface View {
	}
}
