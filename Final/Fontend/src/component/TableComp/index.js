import React from "react";
import { Link } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "./Table.module.scss";
import Button from "../Button";

const cx = classNames.bind(styles);

function TableComp({ headers, bodys, action, onClick }) {
  return (
    <div className={cx("table-container")}>
      <table className={cx("table table-borderd")}>
        <thead className={{ action }}>
          <tr>
            {headers.map((header, index) => (
              <th key={index}>{header}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {bodys.map((body, index) => (
            <tr key={index}>
              <td>{body.id}</td>
              <td>{body.name}</td>
              <td>{body.phone}</td>
              <td>{body.emp_id}</td>
              <td>{body.email}</td>
              <td>{body.company}</td>
              <td>{body.location}</td>
              <td className={cx("text-center")}>
                <Button to={{ pathname: `student/edit/${body.id}` }} primary>
                  Edit
                </Button>
                <span className={cx('thanh-chan')}>&nbsp; | &nbsp;</span>
                <button onClick={onClick}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TableComp;
