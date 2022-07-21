import React, { useState } from "react";
import classNames from "classnames/bind";
import styles from "./Students.module.scss";
import Breadcumb from "../../component/Breadcumb";
import Button from "../../component/Button";
import TableComp from "../../component/TableComp";

const cx = classNames.bind(styles);

const headers = [
  "id",
  "Name",
  "Phone",
  "Email",
  "Emp",
  "Company",
  "Location",
  "Action",
];

const bodys = [
  {
    id: 1,
    name: "Maj",
    phone: "3749871294",
    emp_id: "3fasd",
    email: "manh@gmail.com",
    company: "company",
    location: "location",
  },
  {
    id: 2,
    name: "Maj",
    phone: "3749871294",
    emp_id: "3fasd",
    email: "manh@gmail.com",
    company: "company",
    location: "location",
  },
];

function Students() {
  const [bodyList, setBodyList] = useState(bodys);

  const handleDelete = (e, id) => {
    console.log(e.target.parentElement.parentElement, id);
    setBodyList(bodys.splice(id, 1));
  };

  const handleClick = (e, id) => {
    console.log(e.target, id);
  };

  return (
    <div className={cx("students-container")}>
      <button type="text" onClick={handleClick}>
        text
      </button>
      <Breadcumb />
      <Button primary small>
        Click me!
      </Button>
      <Button primary>Click me!</Button>
      <Button primary>Click me!</Button>
      <div className={cx("card")}>
        <div className={cx("card-header")}>
          <i className="fas fa-table" />
          <span>Employees List</span>
        </div>
        <div className={cx("card-body")}>
          <div className={cx("table-container")}>
            <table className={cx("table table-borderd")}>
              <thead>
                <tr>
                  {headers.map((header, index) => (
                    <th key={index}>{header}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {bodyList.map((body, index) => (
                  <tr key={index}>
                    <td>{body.id}</td>
                    <td>{body.name}</td>
                    <td>{body.phone}</td>
                    <td>{body.emp_id}</td>
                    <td>{body.email}</td>
                    <td>{body.company}</td>
                    <td>{body.location}</td>
                    <td className={cx("text-center")}>
                      <Button
                        to={{ pathname: `student/edit/${body.id}` }}
                        primary
                      >
                        Edit
                      </Button>
                      <span className={cx("thanh-chan")}>&nbsp; | &nbsp;</span>
                      <button onClick={(e) => handleDelete(e, body.id)}>
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Students;
